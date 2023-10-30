# C323 Project 7 - Notes App 2: Evan Tomak

This project is a simple notes app that allows the user to create notes containing a title and a description. The user also has the ability to delete and edit the notes.
This project also features user sign in, sign up, and sign out capabilities through firebase authentication as well as a realtime database.

The functionality is described in more detail below:

## User Screen

[X] The user screen is where the user will have the ability to sign in, sign up, or sign out of the notes app.

[X] If the user does not have an account, they must sign up with their email and password.

[X] If the user does not have an account and they try to sign in or sign out, an error message will be thrown.

[X] The user also must input both the email and password. If either is empty, it will throw an error message when trying to sign up or sign in.

[X] If the user has an account, they must click sign in. If they click sign up, an error message will be thrown.

[X] Upon successful sign in or sign up, the user will be navigated to the notes list fragment.

## Notes List Fragment

[X] The notes list fragment displays all of the notes in a 2-column staggered layout.

[X] Each note has a title and a body.

[X] There is also a toolbar at the top of the notes list. It has a title and two buttons.

[X] The add note button allows the user to add a note.

[X] The user screen button allows the user to navigate back to the user screen to either sign out or sign back in.

[X] 

## Note Screen

##

The following functions/extensions are implemented:

## MainActivity


## Note


## NoteRepository


## NoteRepositorySingleton


## NotesAdapter


## NoteScreen


## NotesListFragment


## NoteViewModel


## NoteViewModelFactory


## User


## UserRepository


## UserRepositorySingleton


## UserScreen


## UserViewModel


## UserViewModelFactory


## Video Walkthrough

Here's a walkthrough of implemented user stories:

## Notes

The biggest issue I faced was getting familiar with firebase, authentication, and realtime database while tailoring it to this specific implementation. Aside from that, transitioning the code from my previous notes app was not too bad, but I did spend a lot of time getting the hang of the firebase implementation.

## License

Copyright 2023 Evan Tomak.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express implied.

See the License for the specific language governing permissions and
limitations under the License.
