# Spring Web Service Maven Archetype

Archetype to setup web service project based on the Spring MVC framework.

This archetype setups the web project for providing web services using Spring MVC project. The archetype
configures persistence layer based on JPA and Hibernate EntityManager as well as full facility to run
integration tests against the application. By default the response format negotiation is configured
providing negotiation of JSON and XML response formats (however new formats may be added easily).

## Features

The project created by this archetype will have configured Spring MVC support as base container. In this
regards application is deployable in any servlets container, and by default it has condigured deployment
to jetty container by running following command:

    mvn jetty:run

Project has configured support for content negotiation. By default supported formats are XML (through
XStream library) and JSON (through Jaxon library). 

The persistance layer is based on JPA and Hibernate EntityManager is choosed as implementation provider.

The archetype comes with fully configured facility for integration testing. This means you can run
all your integration tests with following command:

    mvn verify

Note that for integration tests the failsafe plugin is used. In this regard all integration test classes
must be prefixed with ``IT`` and should not contain ``Test`` suffix, otherwise will be run by the
surefire plugin as unit tests. The ``BaseITTest`` class is given by default in order to serve as base class
for integration test classes and allows to generate HTTP GET requests to the configured URL at which
the application will be available (the URL is configured in ``pom.xml`` file and it is the location
at which application will be available during the integration tests run using above command).

The integration tests uses separate test database. By default the H2 database is confiured for test
database, but it could be easily reconfigured in ``persistence.xml`` file for test resources.

Archetype configures couple of SLF4J and Logback as logging facility.

## Installation and creating project

In order to create project using this archetype checkout or download source, navigate to the archetype's
root directory and type:

     mvn install

Next anywhere you choose type:

     mvn archetype:generate -DarchetypeCatalog=local

Choose option that refer to ``spring-web-service-archetype``, provide ``groupId``, ``artifactId`` and
``servletName``. Last parameter gives information what name should be used for configured servlet and
therefore indicates the name of the Spring MVC's configuration file for this servlet.

If you want import application to eclipse run:

    mvn eclipse:eclipse -Dwtpversion=1.5