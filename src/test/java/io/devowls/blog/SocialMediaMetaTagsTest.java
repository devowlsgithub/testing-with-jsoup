package io.devowls.blog;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SocialMediaMetaTagsTest {

  @Test
  void titleTagsMatchTheArticleTitle() throws IOException {
    String expectedTitle = "The Essential Meta Tags for Social Media | CSS-Tricks";

    Document doc = Jsoup.connect("https://css-tricks.com/essential-meta-tags-social-media/").get();

    String ogTitle = doc.selectFirst("meta[property='og:title']").attr("content");
    String twitterTitle = doc.selectFirst("meta[name='twitter:title']").attr("content");

    assertAll("Asserting titles in meta tags",
        () -> assertEquals(expectedTitle, ogTitle, "Title did not match the expected one in og:title tag"),
        () -> assertEquals(expectedTitle, twitterTitle, "Title did not match the expected one in twitter:title tag"));
  }

  @Test
  void titleTagsMatchTheArticleTitleMultipleElements() throws IOException {
    String expectedTitle = "The Essential Meta Tags for Social Media | CSS-Tricks";

    Document doc = Jsoup.connect("https://css-tricks.com/essential-meta-tags-social-media/").get();

    Elements titleTags = doc.select("meta[name*=':title'],meta[property*=':title']");

    titleTags.forEach(tag -> assertEquals(expectedTitle, tag.attr("content"), "Title did not match the expected one in the tag: " + tag.attr("name")));
  }
}
