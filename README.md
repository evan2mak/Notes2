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

[X] If the user clicks the add note button, they will be navigated to the note screen activity to create a new note.

[X] A user can also click on a note to update or delete the note in the note screen activity.

## Note Screen

[X] The note screen is where note creation takes place.

[X] The user must create a title and a body for the note. If not, an error message will show when save is clicked.

[X] When the user is done creating the note, they can click save, and they will be navigated back to the notes list where the new note will be displayed.

[X] If the user is creating a note for the first time and they no longer want to add the note, they can click the red delete button in the top right to navigate back to the notes list.

[X] If the user wants to update a note, they can click on the note in the notes list and update it here.

[X] If the user wants to delete a note, they can click on the note and press the delete button. They must confirm delete after seeing the dialog.

##

The following functions/extensions are implemented:

## MainActivity

Manages the main user interface and navigation based on user authentication status.

onCreate: 

Initializes Firebase, NavController, UserRepository, and navigates to the appropriate screen based on user authentication.

navigateToUserScreen: 

Navigates to the user authentication or profile screen.

navigateToNotesList: 

Navigates to the screen displaying the list of notes.

## Note

Data class for notes.

## NoteRepository

Manages data operations for notes, including retrieval, addition, update, and deletion.

init: 

Initializes a ValueEventListener to update the list of notes upon any changes in the Firebase Database.

addNote: 

Adds a new note to the Firebase Database.

updateNote: 

Updates an existing note in the Firebase Database.

deleteNote: 

Deletes a note from the Firebase Database.

## NoteRepositorySingleton

Provides a singleton instance of NoteRepository.

## NotesAdapter

Adapter for displaying a list of notes in a RecyclerView.

NoteViewHolder: 

Holds and binds the view for a single note item.

onCreateViewHolder: 

Inflates the note item view and returns a new ViewHolder.

onBindViewHolder: 

Binds a note to a ViewHolder based on its position in the list.

DiffCallback: 

Utility for calculating the difference between two lists to enable efficient updates.

## NoteScreen

Activity for creating and editing notes.

onCreate: 

Initializes the activity, sets up UI elements and event listeners.

loadNoteDetails: 

Loads the details of an existing note into the UI.

deleteNote: 

Deletes the current note.

showDeleteConfirmationDialog: 

Shows a dialog to confirm note deletion.

## NotesListFragment

Fragment to display a list of notes and navigate to note details or user screen.

onCreateView: 

Inflates the layout and initializes the RecyclerView and adapter.

onViewCreated: 

Sets up click listeners for the add note and user screen buttons.

## NoteViewModel

NoteViewModel: 

ViewModel for managing and performing operations on notes.

addNote: 

Adds a new note to the repository.

updateNote: 

Updates an existing note in the repository.

deleteNote: 

Deletes a note from the repository.

## NoteViewModelFactory

Factory class for creating instances of NoteViewModel.

## User

Data class for users.

## UserRepository

Manages user authentication and registration using Firebase.

init:

Initialize the user LiveData based on the current Firebase authenticated user.

login: 

Authenticates a user using email and password.

register: 

Registers a new user using email and password.

saveUserToDatabase: 

Saves the authenticated user's details to Firebase Database.

logout: 

Logs out the authenticated user.

## UserRepositorySingleton

Provides a singleton instance of UserRepository.

## UserScreen

Fragment for user authentication including sign in, sign up, and sign out.

onCreateView: 

Inflates the layout and initializes UI components.

onViewCreated: 

Sets up UI behavior and event listeners.

onDestroyView: 

Removes observers when the view is destroyed.

onSignInClicked: 

Handles sign in button click.

onSignUpClicked: 

Handles sign up button click.

onSignOutClicked: 

Handles sign out button click.

## UserViewModel

ViewModel for managing user authentication and registration.

login: 

Attempts to log in the user with the provided email and password.

register: 

Attempts to register a new user with the provided email and password.

logout: 

Logs out the current authenticated user.

## UserViewModelFactory

Factory class for creating instances of UserViewModel.

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
