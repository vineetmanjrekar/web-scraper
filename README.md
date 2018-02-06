# To run project in local in IntelliJ

In project root:

<code>./gradlew cleanIdea idea</code>

# To run build

In project root:

<code>./gradlew clean build</code>

# To generate executable:

In project root:

<code>./gradlew clean build distZip</code>

Then go to /build/distributions/

unzip the file web-scraper-1.0-SNAPSHOT.zip

cd web-scraper-1.0-SNAPSHOT/bin


Then run  <code>./web-scraper URL_TO_SCRAPE</code>

In this case:

./web-scraper https://jsainsburyplc.github.io/serverside-test/site
/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html