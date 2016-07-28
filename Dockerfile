FROM smartcosmos/service
MAINTAINER SMART COSMOS Platform Core Team

ADD target/smartcosmos-*.jar  /opt/smartcosmos/smartcosmos-edge-things.jar

CMD ["/opt/smartcosmos/smartcosmos-edge-things.jar"]
