package com.example.trivialapp.model

class PreguntasRepositorio(dificultad: String){

    private val preguntasFacil: MutableList<Pregunta> = mutableListOf(
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
        ),
        Pregunta(
            "¿Cuál es la capital de Francia?",
            listOf(
                Respuesta("Lyon", false),
                Respuesta("Marsella", false),
                Respuesta("París", true),
                Respuesta("Burdeos", false)
            )
        ),
        Pregunta(
            "¿Qué animal es el rey de la selva?",
            listOf(
                Respuesta("Elefante", false),
                Respuesta("León", true),
                Respuesta("Tigre", false),
                Respuesta("Jirafa", false)
            )
        ),
        Pregunta(
            "¿Cuántos lados tiene un triángulo?",
            listOf(
                Respuesta("2", false),
                Respuesta("3", true),
                Respuesta("4", false),
                Respuesta("5", false)
            )
        ),
        Pregunta(
            "¿Qué planeta es conocido como el 'planeta azul'?",
            listOf(
                Respuesta("Venus", false),
                Respuesta("Marte", false),
                Respuesta("Tierra", true),
                Respuesta("Júpiter", false)
            )
        ),
        Pregunta(
            "¿Cuál es el río más largo del mundo?",
            listOf(
                Respuesta("Nilo", false),
                Respuesta("Amazonas", true),
                Respuesta("Yangtsé", false),
                Respuesta("Misisipi", false)
            )
        )
    )

    private val preguntasNormal: MutableList<Pregunta> = mutableListOf(
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
        ),
        Pregunta(
            "¿Qué científico formuló la teoría de la relatividad?",
            listOf(
                Respuesta("Isaac Newton", false),
                Respuesta("Albert Einstein", true),
                Respuesta("Stephen Hawking", false),
                Respuesta("Galileo Galilei", false)
            )
        ),
        Pregunta(
            "¿Cuál es el país más grande del mundo por superficie?",
            listOf(
                Respuesta("China", false),
                Respuesta("Estados Unidos", false),
                Respuesta("Canadá", false),
                Respuesta("Rusia", true)
            )
        ),
        Pregunta(
            "¿Qué escritor creó a Harry Potter?",
            listOf(
                Respuesta("J.R.R. Tolkien", false),
                Respuesta("J.K. Rowling", true),
                Respuesta("George R.R. Martin", false),
                Respuesta("Stephen King", false)
            )
        ),
        Pregunta(
            "¿En qué continente se encuentra Egipto?",
            listOf(
                Respuesta("Asia", false),
                Respuesta("Europa", false),
                Respuesta("África", true),
                Respuesta("América", false)
            )
        ),
        Pregunta(
            "¿Cuál es el hueso más largo del cuerpo humano?",
            listOf(
                Respuesta("Tibia", false),
                Respuesta("Peroné", false),
                Respuesta("Húmero", false),
                Respuesta("Fémur", true)
            )
        )
    )

    private val preguntasDificil: MutableList<Pregunta> = mutableListOf(
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
        ),
        Pregunta(
            "¿Cuál es la velocidad de la luz en el vacío?",
            listOf(
                Respuesta("300,000 km/s", true),
                Respuesta("150,000 km/s", false),
                Respuesta("450,000 km/s", false),
                Respuesta("600,000 km/s", false)
            )
        ),
        Pregunta(
            "¿Qué científico desarrolló la teoría de la relatividad?",
            listOf(
                Respuesta("Isaac Newton", false),
                Respuesta("Albert Einstein", true),
                Respuesta("Nikola Tesla", false),
                Respuesta("Galileo Galilei", false)
            )
        ),
        Pregunta(
            "¿Cuál es la fórmula química del ácido sulfúrico?",
            listOf(
                Respuesta("H2SO4", true),
                Respuesta("HCl", false),
                Respuesta("NaCl", false),
                Respuesta("H2CO3", false)
            )
        ),
        Pregunta(
            "¿En qué año llegó el hombre a la Luna por primera vez?",
            listOf(
                Respuesta("1969", true),
                Respuesta("1972", false),
                Respuesta("1965", false),
                Respuesta("1980", false)
            )
        ),
        Pregunta(
            "¿Cuál es el órgano más grande del cuerpo humano?",
            listOf(
                Respuesta("El hígado", false),
                Respuesta("La piel", true),
                Respuesta("El corazón", false),
                Respuesta("El pulmón", false)
            )
        ),
        Pregunta(
            "¿Qué partícula subatómica tiene carga negativa?",
            listOf(
                Respuesta("Protón", false),
                Respuesta("Neutrón", false),
                Respuesta("Electrón", true),
                Respuesta("Positrón", false)
            )
        ),
        Pregunta(
            "¿Cuál es la capital de Mongolia?",
            listOf(
                Respuesta("Ulan Bator", true),
                Respuesta("Astana", false),
                Respuesta("Tiflis", false),
                Respuesta("Bakú", false)
            )
        ),
        Pregunta(
            "¿Qué idioma tiene más hablantes nativos en el mundo?",
            listOf(
                Respuesta("Inglés", false),
                Respuesta("Mandarín", true),
                Respuesta("Español", false),
                Respuesta("Hindi", false)
            )
        )
    )


    fun getPreguntaFacil(): MutableList<Pregunta> {
        return preguntasFacil
    }
    fun getPreguntaNormal(): MutableList<Pregunta> {
        return preguntasNormal
    }

    fun getPreguntaDificil(): MutableList<Pregunta> {
        return preguntasDificil
    }

    fun getRespuestas(pregunta: Pregunta): List<Respuesta> {
        return pregunta.respuesta
    }

}


