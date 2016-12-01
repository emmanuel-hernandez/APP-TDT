CREATE DATABASE IF NOT EXISTS tdt;
USE tdt;

CREATE TABLE IF NOT EXISTS state(
	stateId TINYINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(25),
	shortName VARCHAR(5),
	active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS population(
	populationId INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	stateId TINYINT NOT NULL,
	active BOOLEAN NOT NULL DEFAULT TRUE,
	FOREIGN KEY (stateId) REFERENCES state(stateId)
);

CREATE TABLE IF NOT EXISTS concessionaire(
	concessionaireId SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100),
	active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS concessionType(
	concessionTypeId TINYINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	type VARCHAR(25) NOT NULL,
	description VARCHAR(40) NOT NULL,
	active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS channelBand(
	channelBandId TINYINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(3) NOT NULL,
	description VARCHAR(10) NOT NULL,
	active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS channel(
	channelId SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	distinctive VARCHAR(8) NOT NULL,
	name VARCHAR(50) NOT NULL,
	virtualChannel TINYINT NOT NULL,
	physicChannel TINYINT NOT NULL,
	power SMALLINT NOT NULL,
	acesli TINYINT NOT NULL,
	longitude VARCHAR(15) NOT NULL,
	latitude VARCHAR(15) NOT NULL,
	effectiveDateStart VARCHAR(35) NOT NULL,
	effectiveDateEnd VARCHAR(35) NOT NULL,
	channelBandId TINYINT NOT NULL,
	populationId INT NOT NULL,
	concessionaireId SMALLINT NOT NULL,
	concessionTypeId TINYINT NOT NULL,
	active BOOLEAN NOT NULL DEFAULT TRUE,
	FOREIGN KEY (channelBandId) REFERENCES channelBand(channelBandId),
	FOREIGN KEY (populationId) REFERENCES population(populationId),
	FOREIGN KEY (concessionaireId) REFERENCES concessionaire(concessionaireId),
	FOREIGN KEY (concessionTypeId) REFERENCES concessionType(concessionTypeId)
);


<!-- applicationContext-maintenance-dao.xml -->
	<bean id="informationSendDistributionDAO" class="com.biva.lima.persistence.dao.impl.distribution.InformationSendDistributionDAOImpl">
		<property name="sessionFactory" ref="sessionFactory"></property>
	</bean>

<!-- hibernate-maintenance.xml -->
<mapping class="com.biva.lima.persistence.model.distribution.InformationDistribution"/>
<mapping class="com.biva.lima.persistence.model.distribution.InformationSendDistribution"/>

 <mapping class="com.biva.lima.persistence.model.monitor.InformationSend" />
<mapping class="com.biva.lima.persistence.model.assembly.Assembly" />
<mapping class="com.biva.lima.persistence.model.assembly.AssemblyAgreement" />

<!-- applicationContext-maintenance-services.xml -->
 <context:component-scan base-package="com.biva.lima.maintenance.service.distribution"/>

