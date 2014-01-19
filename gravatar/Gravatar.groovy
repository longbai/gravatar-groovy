package gravatar

import java.security.MessageDigest

class Gravatar {
    static final DEFAULT_SIZE = 80
    private static final GRAVATAR_URL = 'http://www.gravatar.com/avatar/'
    private static final DEFAULT_RATING = Rating.GENERAL_AUDIENCES
    private static final DEFAULT_DEFAULT_IMAGE = DefaultImage.HTTP_404


    private int size = DEFAULT_SIZE;
    private Rating rating = DEFAULT_RATING;
    private DefaultImage defaultImage = DEFAULT_DEFAULT_IMAGE;

    /**
     * Specify a gravatar size between 1 and 512 pixels. If you omit this, a
     * default size of 80 pixels is used.
     */
    def setSize(sizeInPixels){
        if (sizeInPixels < 1 || sizeInPixels > 512) {
            throw new IllegalArgumentException('sizeInPixels needs to be between 1 and 512')
        }
        size = sizeInPixels
    }

    /**
     * Specify a rating to ban gravatar images with explicit content.
     */
    def setRating(theRating){
        if (rating == null) {
            throw new IllegalArgumentException('ratinng not null')
        }
        rating = theRating
    }

    /**
     * Specify the default image to be produced if no gravatar image was found.
     */
    def setDefaultImage(theDefaultImage) {
        if (theDefaultImage == null) {
            throw new IllegalArgumentException('default image not null')
        }
        defaultImage = theDefaultImage;
    }

    /**
     * Returns the Gravatar URL for the given email address.
     */
    def getUrl(email){
        if (email == null) {
            throw new IllegalArgumentException('email not null')
        }
        // hexadecimal MD5 hash of the requested user's lowercased email address
        // with all whitespace trimmed
        def emailHash = md5Hex(email.toLowerCase().trim());
        def params = formatUrlParameters();
        return GRAVATAR_URL + emailHash + '.jpg' + params;
    }

    /**
     * Downloads the gravatar for the given URL using Java {@link URL} and
     * returns a byte array containing the gravatar jpg, returns null if no
     * gravatar was found.
     */
    def download(String email) {
            def url = getUrl(email).toURL()
            return url.getBytes()
    }

    def private static hex(array) {
        return array.encodeHex().toString()
    }

    def static md5Hex (String message) {
        def md = MessageDigest.getInstance('MD5')
        return hex(md.digest(message.getBytes('CP1252')))
    }

    def private sizeParam(){
        if (size == DEFAULT_SIZE) {
            return null
        }
        return 's=' + size
    }

    def private formatUrlParameters() {
        def origin = [
            sizeParam(),
            rating?.genParam(),
            defaultImage?.genParam()
        ]

        def params = origin.grep{
            it != null
        }

        if (params.isEmpty()){
            return ''
        }
        return '?' + params.join('&')
    }

}
