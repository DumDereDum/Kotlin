import java.io.File
import kotlin.math.abs
import kotlin.random.Random
import kotlinx.coroutines.*

fun main()
{
    val minLength       = 6
    val fileData        = "words.txt"
    val fileHistoryData = "games_history.txt"
    val wordSet         = mutableSetOf<String>()
    val longWordsList   = mutableListOf<String>()

    runBlocking {
        File(fileData).useLines { lines -> wordSet.addAll(lines) }
        for (word in wordSet)
            if (word.length > minLength)
                longWordsList.add(word)

        val randWord = longWordsList[abs(Random.nextInt()) % longWordsList.size]
        println("Make up as many words as possible from the letters of the \"$randWord\" " +
                "\nSeparate your words by a space and then press Enter")

        val letterMap = mutableMapOf<Char, Int>()
        for (letter in randWord)
        {
            if (letterMap[letter] === null)
                letterMap[letter] = 1
            else
                letterMap[letter]?.plus(1)
        }

        val userLine = readLine()
        if (userLine != null) {

            val userWords        = userLine.split(" ").toTypedArray()
            val correctUserWords = mutableListOf<String>()
            var score            = 0

            for (word in userWords) {
                val tmpLetterMap  = letterMap.toMutableMap()
                var isWordCorrect = true
                for (letter in word) {
                    if (tmpLetterMap[letter] === null || tmpLetterMap[letter]!! - 1 < 0) {
                        isWordCorrect = false
                        break
                    }
                    tmpLetterMap[letter]?.minus(1)
                }
                if (isWordCorrect)
                    correctUserWords.add(word)
                else
                    println("there is no such letters in your word (\"$word\")")
            }

            val countScore = CoroutineScope(Dispatchers.Default).launch {
                correctUserWords.forEach { word ->
                    if (wordSet.contains(word))
                        score += word.length
                    else
                        println("there is no such word (\"$word\") in our dictionary")
                }
                println("Your score $score")
            }

            val saveHistoryToFile = CoroutineScope(Dispatchers.IO).launch {
                var text = String()
                text += "\n<<< Game >>>\n Word: \"$randWord\" \n WordsHistory: [ "
                for (word in correctUserWords)
                    text += "$word "
                text += "] \n"
                File(fileHistoryData).appendText(text)
            }

            countScore.join()
            saveHistoryToFile.join()

        }
    }
}