import grrsstest.Blog;

class BootStrap {

    def init = { servletContext ->
		
		Blog blg1 = new Blog(titre:'title 1', description:'description 1', content:'long content for blog entry 1')
		blg1.save()
		
		Blog blg2 = new Blog(titre:'title 2', description:'description 2', content:'long content for blog entry 2')
		blg2.save()

		Blog blg3 = new Blog(titre:'title 3', description:'description 3', content:'long content for blog entry 3')
		blg3.save()

    }
    def destroy = {
    }
}
