import gravatar.*

g = new Gravatar()
g.setSize(512)
g.setRating(Rating.GENERAL_AUDIENCES)
g.setDefaultImage(DefaultImage.IDENTICON)

b = g.download('slongbai@gmail.com')

new File('test.jpg').withOutputStream{ out ->
    out.write(b)
}
