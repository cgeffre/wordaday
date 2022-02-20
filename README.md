# WORD-A-DAY

Welcome to Word-A-Day; we're here to help you learn a new word every day! Whether you're an English language learner, studying for a standardized test, or just looking to expand your vocabulary, we've got something for you. You can use this application to:
- Create your own account
- Get random words with short definitions
- Save generated words that you like to your account
- Add specific words you want to learn and automatically get definitions
- View and edit your list of saved words
- Create custom notes to help you study
- Review words using flaschards
- Receive feedback after practicing and review the words you missed
- Control account settings including updating your password or deleting your account

By adding just one word each day and studying regularly, you'll have a copious collection of highfalutin terms in your back pocket in no time!

# SETUP

This application is currently under active development and we anticipate deploying it publicly in early March 2022. In the meantime, you can check out the most recent version by cloning this repository to your preferred IDE and do the following:
- Compile using JDK 11
- Configure a MySQL schema at localhost:3306 with the name "wordoftheday" (or modify application.properties accordingly)
- Set the username and password to "wordoftheday" and enable all permissions

# TECHNICAL INFORMATION

This project was developed in Spring Boot using a Model-View-Controller framework with REST APIs. It is primarily built in Java along with JavaScript, HTML, and CSS, with ThymeLeaf handling templating. The build utilizes Gradle. Random words are generated by a random word API and the application then queries the Merriam-Webster Dictionary API for definitions; a new word is called if no definitions are available. MySQL with Hibernate is used for persistence.

# API INFORMATION

Special thanks to the following APIs that help make this application run:

- Random Word API (https://random-word-api.herokuapp.com/home)
- Merriam-Webster Dictionary API (https://dictionaryapi.com/)
