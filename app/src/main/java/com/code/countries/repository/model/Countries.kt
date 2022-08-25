package com.code.countries.repository.model

import com.squareup.moshi.Json

data class Country(

	@Json(name="capital")
	val capital: String? = null,

	@Json(name="code")
	val code: String? = null,

	@Json(name="flag")
	val flag: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="currency")
	val currency: Currency? = null,

	@Json(name="language")
	val language: Language? = null,

	@Json(name="region")
	val region: String? = null,

	@Json(name="demonym")
	val demonym: String? = null
)

data class Language(

	@Json(name="nativeName")
	val nativeName: String? = null,

	@Json(name="code")
	val code: String? = null,

	@Json(name="iso639_2")
	val iso6392: String? = null,

	@Json(name="name")
	val name: String? = null
)

data class Currency(

	@Json(name="symbol")
	val symbol: String? = null,

	@Json(name="code")
	val code: String? = null,

	@Json(name="name")
	val name: String? = null
)
