# Pre-work - ToDo Organizer

ToDo Organizer is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: Dave Friedman

Time spent: 24 hours spent in total

## User Stories

The following **required** functionality is completed:

* [y] User can **successfully add and remove items** from the todo list
* [y] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [y] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [N] Persist the todo items [into SQLite]  (I DECIDED TO FOCUS MY TIME ELSEWHERE.)(http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [y] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [y] Add support for completion due dates for todo items (and display within listview item)
* [y] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [y] Add support for selecting the priority of each todo item (and display in listview item)
* [y] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [y] List anything else that you can get done to improve the app functionality!
      ** -Added icons so users can easily identify the priority and completion status.
      ** -Employed Spinners to limit data input for 3 enumerations.
      ** -Added an optional task details field.
      ** -Added a text-dynamic confirmation dialog before deletion of a todo task.
      ** -Optimized the ListView with a ViewHolder.
      ** -Added a date picker button and sub-dialog fragment.
      ** -Added a custom icon.
* Future version may:
      ** -Fire off on-device notifications on overdue thresholds.
      ** -Fire off email alerts on overdue thresholds.  (Requires storing configuration data.)
      ** -Offer an API to allow reistering with a server, and publishing todos to / from other users.

## Video Walkthrough 

Here's a walkthrough of implemented user stories:  (http://i.imgur.com/Wb2zKn5.gifv)

<img src='http://i.imgur.com/Wb2zKn5.gifv' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.  [FUN.]
-The click listener did not work with a v5 layout definition; I needed an explicit listener in Java.

## License

    Copyright 2017 by Dave Friedman

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
