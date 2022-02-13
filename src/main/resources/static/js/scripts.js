// Iterates through word flashcards; displays and hides definitions; tracks correct and incorrect answers
function flashcards(wordCount) {
    let counter = 0;
    let correctAnswer = 0;
    let wrongAnswer = 0;
//    let showDefinitions = document.getElementById("showDefinitions");
//    const correctAnswer = document.getElementById("correctAnswer");

    if (counter < wordCount) {
        document.getElementById(counter-1).hidden = true;
        document.getElementById(counter).hidden = false;
    }

//    correctAnswer.addEventListener('click', function(event) {
//        document.getElementById("definitions").hidden = true;
//        counter++;
//    });

//    showDefinitions.addEventListener('click', function() {
//        document.getElementById(definitions).hidden = false;
//    });

}