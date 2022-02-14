// Iterates through word flashcards; displays and hides definitions; tracks correct and incorrect answers
function flashcards(wordCount, scoreDisplay) {
    let counter = 1;
    let rightAnswer = 0;
    let wrongAnswer = 0;

    document.getElementById(counter).hidden = false;

    document.getElementById("rightAnswer").addEventListener('click', function(event) {
        rightAnswer++;
        counter++;
        document.getElementById(counter-1).hidden = true;
        if (counter <= wordCount) {
            document.getElementById(counter).hidden = false;
        }
        if (counter === wordCount+1) {
            document.getElementById("flashcardButtons").hidden = true;
            return scoreDisplay.innerHTML = "You got " + rightAnswer + " out of " + wordCount + " words correct!";
        }
    });

    document.getElementById("wrongAnswer").addEventListener('click', function(event) {
        wrongAnswer++;
        counter++;
        document.getElementById(counter-1).hidden = true;
        if (counter <= wordCount) {
            document.getElementById(counter).hidden = false;
        }
        if (counter === wordCount+1) {
            document.getElementById("flashcardButtons").hidden = true;
            return scoreDisplay.innerHTML = "You got " + rightAnswer + " out of " + wordCount + " words correct!";
        }
    });

    document.getElementById("showDefinitions").addEventListener('click', function() {
        document.getElementById(-(counter)).hidden = false;
    });
}