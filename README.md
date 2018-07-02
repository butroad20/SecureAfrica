# Secure Africa
A project to tackle security challenges in Africa developed during the Hack Ife hackathon organised by ForLoop Africa!

## Problem Statement

There have been incesant security challenges across the continent. Taking Nigeria as an example, we have increased communial clashes like the Fulani heardmens attacks in the middle belt, more cases of kidnapping and armed robbery reported daily. Over the weekend, the heart breaking tanker explosion that occured on the Lagos-Ibadan express road
and the lack of emergency response.  

The following problems were identified:

1. The citizens (or victims) most of the time cannot remember what emergency lines to dail  

Why? The numbers are too long or the victim was in a different state and the emergency number changed. Even when they do, the number might be sometimes unavailable and there is no way to log the incidence.

2. The emergency lines are not toll free.

3. Internet connectivy is not guaranteed.

4. In times of danger, victims tend to be too scared to remember what to do.


### How we tackled the problems

- We implemented an easy to remember USSD code to dail in case of emergency

- Our andriod app gives the user access emergency phone numbers offline.

- During an emergency we abstract all the processes away from the user, all the user has to do is `tap 4 times` or `dail our short ussd code`. We do all the hard work.

## Our Solution

Our solution explained in 3 easy steps:

- Get the victim's location
- Get the nature of emergency
- Get the information to the relevant authority

## Our catergory of users

Two groups of people interract with our platform:

    - Victims: A user who raised an emergency. They can access the platform via USSD or the android application

    - Security Agency: They handle the emergency response and have access to the dashboard where emergency cases are logged


## Our Platform (SecureAfrica)

Our platform is made up of (3) applications nicely integrated together to deliver the optimum value.

### A. The Android APP

This applies to users with smartphone

#### App Flow

  - 1. After the user installs the app, we take the user through a tour on using the app. 

  - 2. When in danger, all the user needs to do is `tap the screen four times` and the security agency gets alerted!

  - 3. How does that work? We automatically detect the user's location and send the alert type and the user's location to the revelant security agency.

  - 4. What happens if internet is down? This is Nigeria. Well we thought of that. The app defaults back to using our USSD code to alert the security agency. This ensure that the incidence gets logged in the database. The app then places a phone call automatically to the right securtiy agency based on the user's location and nature of emergency


#### Features

- User gets access to a list of emeegency numbers based on their location.

- User can add personal emergency numbers to be contacted.

- When sucipious of danger, user can queue an emergency. A floating action button is created. When the danger is confirmed or the threat becomes real, all the user has to do is tap the floating button `4 taps` and the queued emegency gets sent

### B. The USSD APP

The ussd app allows users without access to the interet to create and alert.
The design principle is that we get the user's nature of emergency and location as fast and possible. We log these details on the dashboard so that help can be sent immediately while the we then continue to engage the user so as to get more information from the user.

#### USSD Flow

The USSD app is designed to cater for two nature of emergencies

**Case 1 (Extreme Emegency):** The user is in grief danger and can only manage to dail a ussd code.

For the security agency to take up an emergency case, they need to know the nature of emergency and the location.

Our approach on getting the victim's nature of emergency:

    - A unique code is associated for each nature of emergency

    - eg. dailing `384*0110*1#` automatically informs us that the user is in a robbery situation

Our approach on getting the victims location:

    - We send victim's phone number to the security agency. The security agency can now triangulate the victim's location using the phone number

**Case 2:** The user is reporting an incidence

In these case, the user has enough time to give us enough information. Everything in the emergency case still applies. But now we prompt the user for more information and offer to call the user from our end to get even more information


#### Trying out the USSD APP

- Vistion Online [Simulator](https://simulator.africastalking.com:1517)

- Enter a valid phone number

![Simulator Landing Page](/screenshots/simulator-landing.png)

- Select the USSD Option

![Simulator Options Page](/screenshots/simulator-options.png)

- Enter ussd code to dail (Our ussd code is  `*384*0110#`)

![Simulator USSD Page](/screenshots/simulator-ussd.png)


![Simulator USSD Page](/screenshots/alert-option-list.png)


### The Dashboard

The dashboard allows security agencies to see alerts that have been created by using either from the USSD app or from the mobile app.

![Dashboard](/screenshots/dashboard.png)

#### Testing out the dashboard

- Create an account [here](https://forloop.000webhostapp.com/register)
- Login in to the dashboard

#### Feature of the dashboard

- The dashboard is protected against unauthorized access
- The status of a case can be updated to *processing* when someone has been assigned to the case and later on to *resolved* after the emergency has been duly attendend to.
- Cases can be sorted based on location, type or status

![Sorting and searching example](/screenshots/sort.png)
