// Iterates through word flashcards; displays and hides definitions; tracks correct and incorrect answers
function flashcards(wordCount, scoreDisplay) {
    let counter = 1;
    let rightAnswers = 0;
    let wrongAnswers = [];

    document.getElementById(counter).hidden = false;

    document.getElementById("rightAnswer").addEventListener('click', function(event) {
        rightAnswers++;
        counter++;
        document.getElementById(counter-1).hidden = true;
        if (counter <= wordCount) {
            document.getElementById(counter).hidden = false;
        }
        if (counter === wordCount+1) {
            document.getElementById("flashcardButtons").hidden = true;
            scoreDisplay.innerHTML = "You got " + rightAnswers + " out of " + wordCount + " words correct!";
            if (wrongAnswers.length > 0) {
              studyDisplay.innerHTML = "Here are the words you should review:"
              for (let i = 0; i < wrongAnswers.length; i++) {
                document.getElementById(wrongAnswers[i]).hidden = false;
                document.getElementById(-(wrongAnswers[i])).hidden = false;
              }
            }
        }
    });

    document.getElementById("wrongAnswer").addEventListener('click', function(event) {
        wrongAnswers.push(counter);
        counter++;
        document.getElementById(counter-1).hidden = true;
        if (counter <= wordCount) {
            document.getElementById(counter).hidden = false;
        }
        if (counter === wordCount+1) {
            document.getElementById("flashcardButtons").hidden = true;
            scoreDisplay.innerHTML = "You got " + rightAnswers + " out of " + wordCount + " words correct!";
            if (wrongAnswers.length > 0) {
              studyDisplay.innerHTML = "Here are the words you should review:"
              for (let i = 0; i < wrongAnswers.length; i++) {
                document.getElementById(wrongAnswers[i]).hidden = false;
                document.getElementById(-(wrongAnswers[i])).hidden = false;
              }
            }
        }
    });

    document.getElementById("showDefinitions").addEventListener('click', function() {
        document.getElementById(-(counter)).hidden = false;
    });
}