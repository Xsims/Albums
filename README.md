<h1 align="center">Albums</h1></br>
<p align="center">  
A technical demo Album List app using Kotlin based on modern Android tech-stacks and <b>MVVM</b> architecture.</br>Fetching data from the network and integrating persisted data in the database via repository pattern.<br>
</p>
</br>

<p align="center">
   <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/Java-11-orange.svg?style=flat"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/Xsims/Albums/actions"><img alt="Build Status" src="https://github.com/Xsims/Albums/Build%20APK/badge.svg"/></a>
  <a href="https://github.com/Xsims/Albums/actions"><img alt="Build Status" src="https://github.com/Xsims/Albums/Test/badge.svg"/></a>
</p>

<p align="center">
<img src="/screenshots/list.jpg" width="25%"/>
<img src="/screenshots/details.jpg" width="25%"/>
<img src="/screenshots/error_state.jpg" width="25%"/>
</p>

## Download
Go to the [Releases](https://github.com/Xsims/Albums/releases) to download the latest APK.

<img src="/screenshots/preview.gif" align="right" width="32%"/>

## Tech stack & Open-source libraries

- Requires Java 11
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based
  + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
  + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
  for asynchronous.
- Hilt for dependency injection.
- JetPack
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
- Architecture
  - MVVM Architecture (Declarative View - ViewModel - Model)
  - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging
  network data.
- [Sandwich](https://github.com/skydoves/Sandwich) - construct lightweight http API response and
  handling error responses.

# License

```xml
Designed and developed by 2022 Xsims (Simon GRANGIER)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
