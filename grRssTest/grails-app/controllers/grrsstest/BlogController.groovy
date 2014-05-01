package grrsstest

import com.sun.syndication.io.SyndFeedOutput
import feedsplugin.FeedBuilder
import groovy.xml.StreamingMarkupBuilder

class BlogController {

	def index() {

		return [ all : Blog.list()]
	}

	def feed (long id) {

		def entries = id ? [Blog.get(id)] : Blog.list()
		 
		def builder = new FeedBuilder()
		builder.feed {
			title = "My title"
			link = "http://www.myblogsite.com"
			description = "My Blog"
			entries.each { blog ->
				entry(blog.titre) {
					link = "http://localhost:8080/grRssTest/blog/read/${blog.id}"
					blog.description
				}
			}
		}
		def resultFeed = builder.makeFeed('rss')
		
		// get the raw data
		StringWriter writer = new StringWriter()
		SyndFeedOutput output = new SyndFeedOutput()
		output.output(resultFeed,writer)
		writer.close()
		
		// render as xml 
		render text :
			"<?xml version='1.0' encoding='UTF-8'?>\n"+
			"<?xml-stylesheet href='../../css/rss.css' type='text/css'?>\n"+
			writer.toString().substring(40),
			contentType: "text/xml", 
			encoding: "UTF-8"
	}

	def read( long id){
		Blog blog = Blog.get(id)
		return [blog:blog]
	}
}
