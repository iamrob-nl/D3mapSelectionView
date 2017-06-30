# D3mapSelectionView

Based on the D3 library. This is a Mendix widget with a map selection screen with dynamic data that comes from a JSON structure within Mendix. When selecting a country on the map, it will show a list with items and a status each item has.

## configurable
- Select a microflow to get the JSON structure that is used for the map.
- Color array: give all the countries a default color and define colors based on the amount of items that each country contains.
- Threshold array: an array for showing a country in a given color. i.e.: a country can be red by default, and colored green in the case a country contains more than 5 items.

## testproject
The JSON is added to this project as a testfile. On the homepage, browse to the JSON file for the structure and 'Retrieve JSON'. the JSON can then be viewed. The map page will then show the working widget.

## testfile
The orders.json file is located in the test folder. This file contains some testdata in the format which the widget will expect.
