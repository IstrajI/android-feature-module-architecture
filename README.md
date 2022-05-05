# Git Hub Search Technical Challenge
### Requirements
Please write a native Android application that allows the user to search through GitHub open source repositories. The application should have a text field in which the user enters a search phrase, a list that displays the search results, and after the user taps on a list item, the website of the repository should be displayed in a web browser.

Please use the GitHub search API in order to perform the search: [https://developer.github.com/v3/search](https://developer.github.com/v3/search).
Check in the code to an open GitHub repository and send us a link or a private one and invite srgtuszy after you're done with the task.

There are no rules or guidelines on how to perform the task apart from the ones mentioned above. Please implement it in whatever way you think is best based on your judgment and experience. It's your time to shine! Good luck!

### What were used
* Kotlin
* MVVM
* LiveData
* ViewModel
* Dagger2
* Navigation Components
* Retrofit2
* Single Activity Architecture
* Custom Fonts
* Light/Dark theme

### Architecture
The app has following packages:

* app - as an entry point, di, and root activity
* ui-search - search functionality
* ui-components - common components for ui-feature modules
* domain - layer with bisiness logic for orchestration of the datasources
* datasource - sources of data, for the challenge only network
* core - module for common of all the modules

### Screenshots
<div id="screenshot3">    
    <img src="https://user-images.githubusercontent.com/9091709/166574114-1797d00d-2e0b-4eee-ac06-a7f9eb040c23.jpg"  width="432" height="936"/>
    <img src="https://user-images.githubusercontent.com/9091709/166574118-45f71a09-7044-4618-ab7c-45550773374a.jpg"  width="432" height="936"/>
</div>

<div id="screenshot3">
    <img src="https://user-images.githubusercontent.com/9091709/166574122-64bd63a9-cd99-4c48-a24c-f636c134040d.jpg"  width="432" height="936"/>
    <img src="https://user-images.githubusercontent.com/9091709/166574124-8ff9577d-1f72-4d7c-bdb7-7b447950b796.jpg"  width="432" height="936"/>
</div>
