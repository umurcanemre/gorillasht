package com.umurcan.gorillas

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.umurcan.gorillas.domain.User
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.io.File
import java.lang.reflect.Type
import java.util.*

@SpringBootApplication
class GorillasTaskApplication{

	@Bean
	fun loadUsers() : List<User> {
		val jsonString: String = File("./src/main/resources/users.json").readText(Charsets.UTF_8)

		val listType: Type = object : TypeToken<List<User>>() {}.type
		val users:List<User> = Gson().fromJson(jsonString, listType)
		println("USERS READING DONE")
		return Collections.unmodifiableList(users)
	}
}

//TO-consider for later : add graceful shutdown

fun main(args: Array<String>) {
	runApplication<GorillasTaskApplication>(*args)
}