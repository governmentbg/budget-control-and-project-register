package bg.infosys.daeu.db.dto;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.hibernate.query.criteria.internal.BasicPathUsageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.infosys.common.db.dao.aux.ListModelFilter;

public class CustomDaoUtils {
	private static final Logger logger = LoggerFactory.getLogger(CustomDaoUtils.class);
	
	/**
	 * Builds multiselect on <code><b>root</b></code> and filter <code><b>orderBy</b></code> field
	 * 
	 * @param <T> The type of root
	 * 
	 * @param builder Your criteria builder
	 * @param root Your root type in the from clause
	 * @param criteria Your criteria query
	 * @param filter ListModelFilter
	 */
	private CustomDaoUtils() {}
	
	public static final <T> void setCriteriaFilterProps(CriteriaBuilder builder, Root<T> root, CriteriaQuery<?> criteria, ListModelFilter filter) {
		root.alias("root");
		if (filter != null && filter.getOrderBy() != null) {
			String fieldName = filter.getOrderBy();
			Expression<?> selection = (Expression<?>) getSelection(root, fieldName);
			
			if (filter.isAscending()) {
				criteria.multiselect(root, selection).orderBy(builder.asc(selection)).distinct(true);
			} else {
				criteria.multiselect(root, selection).orderBy(builder.desc(selection)).distinct(true);
			}
		}
	}
	
	/**
	 * Reflectively reads <code><b>dtoClass</b></code> fields and their correspondent annotations and builds multiselect based on the field paths</br>
	 * Orders by filter <code><b>orderBy</b></code>
	 * 
	 * If no filter is passed to the arguments, no orderBy will be applied to the query
	 * 
	 * @param <T> The type of root
	 * @param <C> The type of DTO class
	 * 
	 * @param builder Your criteria builder
	 * @param root Your root type in the from clause
	 * @param criteria Your criteria query
	 * @param filter ListModelFilter
	 * @param dtoClass The DTO class with annotated fields
	 */
	public static final <T, C> void setCriteriaFilterPropsDTO(CriteriaBuilder builder, Root<T> root, CriteriaQuery<C> criteria, ListModelFilter filter, Class<C> dtoClass) {
		root.alias("root");
		final List<Expression<?>> selections = buildProjectionSelections(root, dtoClass);
		
		if (filter != null && filter.getOrderBy() != null) {
			final List<Field> fields = Arrays.asList(dtoClass.getDeclaredFields());		
			try {		
				String fieldName = filter.getOrderBy();
				final Optional<Field> fieldOpt = fields.stream().filter(field -> field.getName().equals(fieldName)).findFirst();
				if(fieldOpt.isEmpty())
					throw new NoSuchFieldException();
							
				final ProjectionField annotation = fieldOpt.get().getAnnotation(ProjectionField.class);
				if(annotation == null) 
					throw new RuntimeException("No annotation 'ProjectionField' on " + fieldName + " in " + dtoClass.getName());
				
				final String orderFieldAlias = annotation.path().replaceAll("\\.", "_");
				final Optional<Expression<?>> orderSelectionOpt = selections.stream().filter(selection -> selection.getAlias().equals(orderFieldAlias)).findFirst();
				Expression<?> orderSelection;
				if(orderSelectionOpt.isEmpty()) {
					orderSelection = (Expression<?>) getSelection(root, annotation.path());
					selections.add(orderSelection);
				} else {
					orderSelection = orderSelectionOpt.get();
				}
				
				if (filter.isAscending()) {
					criteria.multiselect(selections.toArray(new Expression<?> [selections.size()])).orderBy(builder.asc(orderSelection)).distinct(true);
				} else {
					criteria.multiselect(selections.toArray(new Expression<?> [selections.size()])).orderBy(builder.desc(orderSelection)).distinct(true);
				}
			} catch (NoSuchFieldException | SecurityException e) {
            	logger.error("Could not find '" + root + "': no such field!");
			}
		} else {
			criteria.multiselect(selections.toArray(new Expression<?> [selections.size()]));
		}
	}
	

    /**
     * Recursively <code>Hibernate.<i>initialize</i></code> references of <code><b>entity</b></code> <u>ONLY</u> on the specified paths
     * 
     * @param entity The given entity to force load
     * @param paths The paths which to initialize
	 * @param alreadyChecked List containing already checked objects in order to avoid StackOverflow exception, should pass empty list
      /
    public static <T> void forceLoadSelectedCollections(T entity, Collection<String> paths, List<Object> alreadyChecked) {
        if (alreadyChecked == null) {
            alreadyChecked = new ArrayList<>();
        }
        if (entity == null) {
            return;
        }
        alreadyChecked.add(entity);
        
        Set<String> roots = paths.stream().map(path -> path.split("\\.")[0]).collect(Collectors.toSet());
        for(String root : roots) {
        	Field field;
			try {
				Optional<Field> optionalField = Arrays.stream(entity.getClass().getDeclaredFields()).filter(f -> f.getName().equals(root)).findFirst();
				if(optionalField == null || optionalField.isEmpty())
					throw new NoSuchFieldException();
				
				field = optionalField.get();
				
	        	Set<String> subpaths = paths.stream()
	        								.filter(path -> path.startsWith(root + "."))
	        								.map(path -> path.replaceFirst("\\w+\\.", ""))
	        								.collect(Collectors.toSet());
	        	
	        	try {
	                field.setAccessible(true);
	                if (field.getAnnotation(OneToOne.class) != null || field.getAnnotation(ManyToOne.class) != null) {
	                    if (field.get(entity) != null) {
	                        if (!Hibernate.isInitialized(field.get(entity))) {
	                            Hibernate.initialize(field.get(entity));
	                        }
	   
	                        Object unproxedEntity = Hibernate.unproxy(field.get(entity));
	                        field.set(entity, unproxedEntity);
	                        if (unproxedEntity != null && !alreadyChecked.contains(field.get(entity))) {
	                        	forceLoadSelectedCollections(unproxedEntity, subpaths, alreadyChecked);
	                        }
	                    }
	                } else if (field.getAnnotation(OneToMany.class) != null || field.getAnnotation(ManyToMany.class) != null) {
	                    if(!Hibernate.isInitialized(field.get(entity))) 
	                    	Hibernate.initialize(field.get(entity));
	
                        Object[] values = null;
                        if (field.get(entity) instanceof Collection) {
                            values = ((Collection<?>)field.get(entity)).toArray();
                        } 
                       
                        if (values != null) {
                            for (Object value : values) {
                                value = Hibernate.unproxy(value);
                                if (value != null && !alreadyChecked.contains(value)) {
                                	forceLoadSelectedCollections(value, subpaths, alreadyChecked);
                                }
                            }
                        }
	                }
	            } catch (IllegalAccessException e) {
	                logger.error("Unable to force initialize field: " + field.getName());
	            }
        	} catch (NoSuchFieldException | SecurityException e1) {
            	logger.error("Could not fetch '" + root + "': no such field on type '" + entity.getClass() + "'!");
			}
        }
    }
    
    /**
     * Recursively <code>Hibernate.<i>initialize</i></code> all references of <code><b>entity</b></code>
     * 
     * @param entity The given entity to force load
	 * @param alreadyChecked List containing already checked objects in order to avoid StackOverflow exception, should pass empty list
      /
    public static <T> void forceLoadAllCollections(T entity, Set<Object> alreadyChecked) {
    	 if (alreadyChecked == null) {
             alreadyChecked = new LinkedHashSet<>();
         }
         if (entity == null) {
             return;
         }
         alreadyChecked.add(entity);
         
         for (Field field : entity.getClass().getDeclaredFields()) {
             try {
                 field.setAccessible(true);
                 if (field.getAnnotation(OneToOne.class) != null || field.getAnnotation(ManyToOne.class) != null) {
                     if (field.get(entity) != null) {
                         if (!Hibernate.isInitialized(field.get(entity))) {
                             Hibernate.initialize(field.get(entity));
                         }
    
                         Object unproxedEntity = Hibernate.unproxy(field.get(entity));
                         field.set(entity, unproxedEntity);
                         if (unproxedEntity != null && !alreadyChecked.contains(field.get(entity))) {
                        	 forceLoadAllCollections(unproxedEntity, alreadyChecked);
                         }
                     }
                 } else if (field.getAnnotation(OneToMany.class) != null || field.getAnnotation(ManyToMany.class) != null) {
                     if(!Hibernate.isInitialized(field.get(entity)))
                     	Hibernate.initialize(field.get(entity));

                     Collection<?> values = null;
                     if (field.get(entity) instanceof Collection) {
                         values = (Collection<?>) field.get(entity);
                     } 
                    
                     if (values != null) {
                         for (Object value : values) {
                             value = Hibernate.unproxy(value);
                             if (value != null && !alreadyChecked.contains(value)) {
                            	 forceLoadAllCollections(value, alreadyChecked);
                             }
                         }
                     }
                 }
             } catch (IllegalAccessException e) {
                 logger.error("Unable to force initialize field: " + field.getName());
             }
         }
    }
	
	
	public static final String lowerWild(String value) {
		return "%".concat(value).concat("%").toLowerCase();
	}
	
	/**
	 * Reflectively reads <code><b>dtoClass</b></code> fields and their correspondent annotations and returns a list with selections made on <code><b>root</b></code>
	 * 
	 * @param <T> The type of root
	 * @param <C> The type of DTO class
	 * 
	 * @param root Your root type in the from clause
	 * @param dtoClass The DTO class with annotated fields
	 * 
	 * @return A list containing all selections made for the multiselect
	 */
	private static List<Expression<?>> buildProjectionSelections(From<?, ?> root, Class<?> dtoClass) {
		final List<Expression<?>> result = new LinkedList<> ();
		for(Field field : dtoClass.getDeclaredFields()) {
			final ProjectionField annotation = field.getAnnotation(ProjectionField.class);
			if(annotation == null)
				continue;
			
			result.add((Expression<?>) getSelection(root, annotation.path()));
		}
		return result;
	}
	
	/**
	 * Recursively initializes a <code><b>Selection</b></code> staring for root, and following a path ("foo.bar.field") 
	 *  
	 * @param root Your root type in the from clause
	 * @param selection The selection path
	 * 
	 * @return The selection
	 */
	private static Selection<?> getSelection(From<?, ?> root, String selection) {
		if(selection.contains(".")) {
			final String joinName = selection.substring(0, selection.indexOf('.'));
			
			Join<?, ?> join = root.join(joinName, JoinType.LEFT);
			join.alias((root instanceof Join<?, ?> ? root.getAlias() + "_" : "") + joinName);
			return getSelection(join, selection.substring(selection.indexOf('.') + 1, selection.length()));
		} else {
			Path<?> result;
			try {
				result = root.join(selection, JoinType.LEFT);
			} catch(BasicPathUsageException bpue) {
				result = root.get(selection);
			}
			result.alias((root instanceof Join<?, ?> ? root.getAlias() + "_" : "") + selection);
			return result;
		}
	}
}
