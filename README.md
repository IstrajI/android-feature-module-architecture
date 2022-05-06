# Android Feature Module Architecture
### Summary
Example of multimodule complex android architecture, wich is ready to use in a big projects

API: [https://developer.github.com/v3/search](https://developer.github.com/v3/search).

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
