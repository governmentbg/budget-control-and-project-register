package bg.infosys.daeu.ws.integrations;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.apache.cxf.helpers.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import bg.infosys.daeu.db.entity.pub.ETranslation;
import bg.infosys.daeu.ws.services.ETranslationService;
import bg.infosys.daeu.ws.wsbg.etranslate.ETranslateRequestObject;
import bg.infosys.daeu.ws.wsbg.etranslate.ETranslateRequestObject.CallerInformation;

@Component
public class ETranslateRequestBuilder {

	@Autowired
	private ETranslationService etranslationService;
	
    private static final String URL = "https://webgate.ec.europa.eu/etranslation/si/translate";
//    private static final Logger logger = LoggerFactory.getLogger(ETranslateRequestBuilder.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClientBuilder clientBuilder;
//    private static final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();


    @Value("${eTranslate.username}")
    private String username;
    @Value("${eTranslate.password}")
    private String password;

    public ETranslateRequestBuilder() {
        clientBuilder = HttpClientBuilder.create();
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        clientBuilder.setDefaultCredentialsProvider(credentialsProvider);

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        clientBuilder.setRedirectStrategy(redirectStrategy);

    }

    @SuppressWarnings("deprecation")
	public Integer buildRequest(ETranslateRequestObject requestObject) throws IOException {
    	requestObject.setCallerInformation(new CallerInformation("eGOV_BG_BudgetCS_20200211", username));
    	
    	requestObject.setTextToTranslate(URLDecoder.decode(requestObject.getTextToTranslate(),"UTF-8"));
        String stringObject = objectMapper.writeValueAsString(requestObject);
        DefaultHttpClient client = new DefaultHttpClient();
        client.getCredentialsProvider().setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(username, password));
        HttpPost post = new HttpPost(URL);
        post.setEntity(new StringEntity(stringObject,ContentType.APPLICATION_JSON));
        HttpClientParams.setRedirecting(post.getParams(), false);
        HttpResponse response = client.execute(post);
        String text = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8.name());
        System.out.println(text);
        ETranslation translation = new ETranslation();
        translation.setRequestId(Integer.parseInt(text));
        etranslationService.saveTranslation(translation);
        System.out.println("translation.getId(): "+translation.getId());
        return translation.getId();
    }

	public ETranslation findTranslationByRequestId(String idRequest) {
		return etranslationService.findByRequestId(Integer.parseInt(idRequest));
	}

	public void saveTranslation(ETranslation translation) {
		etranslationService.saveTranslation(translation);
	}

}
