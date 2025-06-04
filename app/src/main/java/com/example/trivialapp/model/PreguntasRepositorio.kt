package com.example.trivialapp.model

class PreguntasRepositorio{

    private val preguntas1:MutableList<Pregunta> = mutableListOf(
        Pregunta(
            "¿En qué país se encuentra la Torre Eiffel?",
            listOf(
                Respuesta("Italia", false),
                Respuesta("Francia", true),
                Respuesta("España", false),
                Respuesta("Alemania", false)
            )
        ),
        Pregunta(
            "¿Quién escribió 'Don Quijote de la Mancha'?",
            listOf(
                Respuesta("Gabriel García Márquez", false),
                Respuesta("Miguel de Cervantes", true),
                Respuesta("Federico García Lorca", false),
                Respuesta("Mario Vargas Llosa", false)
            )
        ),
        Pregunta(
            "¿Cuál es el océano más grande del mundo?",
            listOf(
                Respuesta("Atlántico", false),
                Respuesta("Índico", false),
                Respuesta("Pacífico", true),
                Respuesta("Ártico", false)
            )
        ),
        Pregunta(
            "¿Cuál es el país con mayor cantidad de habitantes en el mundo?",
            listOf(
                Respuesta("India", false),
                Respuesta("Estados Unidos", false),
                Respuesta("China", true),
                Respuesta("Rusia", false)
            )
        ),
        Pregunta(
            "¿Cuántos continentes hay en la Tierra?",
            listOf(
                Respuesta("5", false),
                Respuesta("6", false),
                Respuesta("7", true),
                Respuesta("8", false)
            )
        )
    )

    private val preguntas2:MutableList<Pregunta> =  mutableListOf(
        Pregunta(
            "¿Cuál es la moneda oficial del Reino Unido?",
            listOf(
                Respuesta("Euro", false),
                Respuesta("Libra esterlina", true),
                Respuesta("Dólar", false),
                Respuesta("Yen", false)
            )
        ),
        Pregunta(
            "¿Quién pintó 'La última cena'?",
            listOf(
                Respuesta("Vincent van Gogh", false),
                Respuesta("Pablo Picasso", false),
                Respuesta("Leonardo da Vinci", true),
                Respuesta("Salvador Dalí", false)
            )
        ),
        Pregunta(
            "¿En qué año comenzó la Segunda Guerra Mundial?",
            listOf(
                Respuesta("1914", false),
                Respuesta("1939", true),
                Respuesta("1945", false),
                Respuesta("1950", false)
            )
        ),
        Pregunta(
            "¿Cuál es el metal más valioso en la actualidad?",
            listOf(
                Respuesta("Oro", false),
                Respuesta("Platino", false),
                Respuesta("Rodio", true),
                Respuesta("Paladio", false)
            )
        ),
        Pregunta(
            "¿Cuál es el idioma más hablado en el mundo?",
            listOf(
                Respuesta("Inglés", false),
                Respuesta("Español", false),
                Respuesta("Chino mandarín", true),
                Respuesta("Hindi", false)
            )
        )
    )

    private val preguntas3:MutableList<Pregunta> = mutableListOf(
        Pregunta(
            "¿Cuál es el elemento más abundante en la corteza terrestre?",
            listOf(
                Respuesta("Hierro", false),
                Respuesta("Oxígeno", true),
                Respuesta("Silicio", false),
                Respuesta("Carbono", false)
            )
        ),
        Pregunta(
            "¿Qué planeta es conocido como el 'Planeta Rojo'?",
            listOf(
                Respuesta("Venus", false),
                Respuesta("Júpiter", false),
                Respuesta("Marte", true),
                Respuesta("Saturno", false)
            )
        )
    )

    fun getPreguntas1(): MutableList<Pregunta>{
        return preguntas1
    }

    fun getPreguntas2(): MutableList<Pregunta>{
        return preguntas2
    }

    fun getPreguntas3(): MutableList<Pregunta>{
        return preguntas3
    }
}


