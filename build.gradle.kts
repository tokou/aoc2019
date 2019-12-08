
plugins {
    kotlin("jvm") version "1.3.61"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("script-runtime"))
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit:junit:4.12")
}
