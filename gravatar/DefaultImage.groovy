package gravatar
enum DefaultImage {
    GRAVATAR_ICON(''),
    IDENTICON('identicon'),
    MONSTERID('monsterid'),
    WAVATAR('wavatar'),
    HTTP_404('404');

    private final String code

    private DefaultImage(theCode) {
        code = theCode
    }

    def genParam(){
        if (this == GRAVATAR_ICON) {
            return null
        }
        return 'd=' + code
    }
}
