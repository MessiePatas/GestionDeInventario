plugins {
	java
	id("org.springframework.boot") version "2.7.15"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	jacoco
	id("info.solidsoft.pitest")version "1.9.0"
	id("org.sonarqube") version "4.4.1.3373"
}

group = "co.gestor"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {


	implementation ("io.springfox:springfox-swagger2:2.9.2")
	implementation ("io.springfox:springfox-swagger-ui:2.9.2")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("mysql:mysql-connector-java:8.0.30")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.data:spring-data-jpa:2.7.15")
	compileOnly("org.projectlombok:lombok:1.18.28")
	annotationProcessor("org.projectlombok:lombok:1.18.28")
	implementation("com.h2database:h2:2.2.220")
	testImplementation("com.h2database:h2:2.2.224")
	implementation("org.springframework.boot:spring-boot-starter-security")

	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
//	implementation("org.mindrot.bcrypt:bcrypt:0.3")




}

tasks.withType<Test> {
	useJUnitPlatform()
}
tasks.test {
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		csv.required.set(true)
		xml.required.set(true)


	}
}

jacoco {
	toolVersion = "0.8.8"
}


afterEvaluate{
	tasks.named<JacocoReport>("jacocoTestReport"){
		classDirectories.setFrom(classDirectories.files.map { dir ->
			fileTree(dir){
				exclude(
						"co/gestor/Inventario/controller/DTO",
						"co/gestor/Inventario/modelo",
					"co/gestor/Inventario/seguridad",
					"co/gestor/Inventario/controller/LoginController.java"
				)
			}
		})
	}

	pitest{
		junit5PluginVersion.set("1.0.0")
		excludedClasses.addAll("co/gestor/Inventario/controller/DTO.**",
		"co/gestor/Inventario/modelo.**","co/gestor/Inventario/controller.**", "co/gestor/Inventario/seguridad.**",
			"co/gestor/Inventario/controller/LoginController.**","co/gestor/Inventario/controller/LoginController")



	}

	sonarqube {
		properties {
			property("sonar.projectName", "Inventario")
		}
	}

}
