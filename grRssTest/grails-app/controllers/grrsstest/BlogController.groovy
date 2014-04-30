package grrsstest

class BlogController {

    def index() {
		
		return [ all : Blog.list()]
		
	}
	
	def feed () {
		render(feedType:"rss", feedVersion:"2.0") {
		   title = "My Blog"
		   link = "http://:8080/grRssTest/blog/feed"
		   description = "A fee blog test"
		   Blog.list().each { blog ->
			  entry(blog.titre) {
				 link = "http://localhost:8080/grRssTest/blog/read/${blog.id}"
				 blog.description
			  }
		   }
		}
	 }
	
	def read( long id){
		Blog blog = Blog.get(id)
		return [blog:blog]
	}
}
