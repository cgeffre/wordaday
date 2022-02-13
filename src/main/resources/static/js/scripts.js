// Iterates through word flashcards; displays and hides definitions; tracks correct and incorrect answers
function flashcards(wordCount) {
    let counter = 1;
    let rightAnswer = 0;
    let wrongAnswer = 0;

    document.getElementById(counter).hidden = false;

    document.getElementById("rightAnswer").addEventListener('click', function(event) {
        document.getElementById(counter).hidden = true;
        document.getElementById(counter+1).hidden = false;
        rightAnswer++;
        counter++;
    });

    document.getElementById("wrongAnswer").addEventListener('click', function(event) {
        document.getElementById(counter).hidden = true;
        document.getElementById(counter+1).hidden = false;
        wrongAnswer++;
        counter++;
    });

    document.getElementById("showDefinitions").addEventListener('click', function() {
        document.getElementById(-(counter)).hidden = false;
    });

}