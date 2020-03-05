# Dicom-MetaData-Extract

This project provides a basic meta data extractor from a DICOM file

It deploys a Swing application asking to locate the .dcm file extension and it will load the meta data in a table.
The Dicom Files for an example can be found in the Sample Dicom folder.
Dicom images are taken from 'www.dicomlibrary.com', 'www.rubomedical.com'

### Tech Stack
<ul>
<li>PixelMed library
<li>Swing
</ul>

### Run the project

Make sure that Maven is in your System Variables
- Install plugin before resolving dependencies
```
mvn org.apache.maven.plugins:maven-install-plugin:2.5.1:install-file \
-Dfile=lib/cloudfoundry-client-lib-shaded-1.0.3.jar 
```

- Configure the dependencies and build the package
```
mvn install
```

- Run the `Main` method in `App.java` by running 
```
mvn exec:java
```
This is popup an GUI application now go on and click browse to locate an .dcm file.

