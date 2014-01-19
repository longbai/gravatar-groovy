package gravatar
enum Rating{
    GENERAL_AUDIENCES('g'),
    PARENTAL_GUIDANCE_SUGGESTED('pg'),
    RESTRICTED('r'),
    XPLICIT('x');

    private final String code

    private Rating(theCode) {
            code = theCode
    }

    def genParam(){
        if (this == GENERAL_AUDIENCES) {
            return null
        }
        return 'r=' + code
    }
}
