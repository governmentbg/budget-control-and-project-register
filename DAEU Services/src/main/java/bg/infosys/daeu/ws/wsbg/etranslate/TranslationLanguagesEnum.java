package bg.infosys.daeu.ws.wsbg.etranslate;

public enum TranslationLanguagesEnum {
    EN("EN"),
    FR("FR"),
    BG("BG");

    private final String language;

    TranslationLanguagesEnum(String language){
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
