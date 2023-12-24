## Introduction
## Motivation and Value
With people going back to in-person normalcy, there is an increase of demand for in-person events. However, the limiting factors for attending events are proximity, money, and time. EventMe is a solution to this that helps users find curated events fitting their needs.

## Product Scope
EventMe is a convenient application that will help users find free and/or cheap local events and register for them.

# Main Functions
- Explore the nearby events 
- Search based on event type (e.g. music, arts, outdoor, etc) through buttons or tabs 
- Search bar search for name of the event, location, or sponsoring organization 
- Indicate range of dates to find only those events that fit the timeframe 
- All of these events can be sorted from in the “Event Box” for:
  - Lowest to highest cost (default)
  - Proximity
  - Earliest to latest date
  - Alphabetical

# Map
- All events shown as markers 
- When an event is clicked on, it will show in the “Event Box” 
- Swiping up from the “Event Box” will take user back to map

# Register 
On Events Registration page, users can see all information regarding the events 
When clicking on the Register button: 
  - If user not logged in, redirect to Login/Register 
  - If logged in and has not registered, successfully register ONLY if there is no conflicting events 
  - If conflict, warn user 
  - If they want to proceed, they can 
  - If logged in and has registered, ask to unregister 
  - If yes, event removed from the user’s registration 
 
All events will be shown in the Profile Tab

# Business Context
The application is similar to Eventbrite. Eventbrite lets you browse, create, and promote local events. Also, a feature in Facebook, events, is a similar context where you can create local events. The difference is that we do not sell tickets but rather allow users to register. Also, we are different from Facebook's function because we do not require a social media connection or login to register for an event. 
