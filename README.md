# 🧪 Android Code Challenge for Palo IT/Credijusto

## Libraries

- `Jetpack Compose` to develop the UI.
- `Hilt` for dependency injection.
- `Ktor` is the `http client`.
- `Mockk` to generate mocks/stubs.
- `Kotest` for readable tests assertions.
- `Turbine` to test painless `Flows`.

## What do I expect to see?

This application contains 2 activities:

- [PostsActivity]: Shows a list of posts displaying only its title. This is the _launch_ activity.
- [DetailsActivity]: Shows detailed information about a post such as title, body, author and comments.

Unfortunately, there is no cool animations and things like that. But at least, __I gave it a little bit of love__ adding `Loader` and `Error` states; which could be tested cutting off all the internet access.

### Error state

![Error state]

## Loading state

![Loading state]

### Peek on `PostsActivity`

If everything goes fine, and the data loads successfully. You should see something like the image below.

![PostsScreen]

### Peek on `DetailsActivity`

After tap on any enlisted post, you should se something like this:

![DetailsScreen]

## Diving into the technical part

The application is a MVVM - Clean Architecture. The layers starting from outside are:
ui, di; repositories, data sources; and domain models.

### UI and DI layer

Here you'll find all the Android things such as _composable functions_, _activities_, _view models_, _theming_ and the modules for dependencies.

Another workaround is move each _DI_ into the respective module.

### Repositories and Data sources

The data sources are divided in 2: `remote` and `local`. The idea is, the `remote` will return the data from any network based data source such as _Rest API_, _SOAP_, _GraphQL_, etc... and the `local` will return data from a _local database_ such SQLite, memory, etc...

The `repositories` handle the received data and exceptions given by the `datasource` and transform all to _business language_.

It's true you can make this _cleaner_ doing each datasource return `Result<Domain>` instead do this in the repository. But hey, _it is what it is!_

## Future enhancements

- Improve the UI. Currently sucks.
- Better error handling. Now, when an error occurs the user doesn't know what the ~fuck~ its happening.
    - Translate the framework errors to business errors.
    - Give to the user alternatives. Like "click here to refresh".
- More unit tests.
- Include instrumentation tests and integration tests.
- Improve the caching.

[PostsActivity]: app/src/main/java/com/github/ephelsa/credijustotest/ui/screens/posts/PostsActivity.kt
[DetailsActivity]: app/src/main/java/com/github/ephelsa/credijustotest/ui/screens/details/DetailsActivity.kt
[PostsScreen]: images/post_screen.png
[DetailsScreen]: images/details_screen.png
[Error state]: images/error_state.png
[Loading state]: images/loading_state.png