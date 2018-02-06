# To run project in local in IntelliJ

In project root:

./gradlew cleanIdea idea

#To run build

In project root:

./gradlew clean build

#To generate executable:

In project root:

./gradlew clean build distZip

Then go to /build/distributions/

unzip the file web-scraper-1.0-SNAPSHOT.zip

cd web-scraper-1.0-SNAPSHOT/bin


Then run ./web-scraper <url>

In this case:

./web-scraper https://jsainsburyplc.github.io/serverside-test/site
/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html