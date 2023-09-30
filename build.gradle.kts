plugins {
	java
	id("org.springframework.boot") version "2.7.15"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	jacoco
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
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("mysql:mysql-connector-java:8.0.30")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.data:spring-data-jpa:2.7.15")
	compileOnly("org.projectlombok:lombok:1.18.28")
	annotationProcessor("org.projectlombok:lombok:1.18.28")
	implementation("com.h2database:h2:2.2.220")
	testImplementation("com.h2database:h2:1.4.200")
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
						"co/gestor/Inventario/modelo"
				)
			}
		})
	}
}
