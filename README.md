# ShowMeTheBill
A bill creation app using room annotations and LiveData based on google's udacity.com.
There are three levels of bill type.
An example is a plumber can have in the first level bill type faucet repair,
and in the second level in faucet repair he can have cartridge change or fix leaky pipes etc..
and in the third level in cartridge change will be all different types of cartridges.

Bill would normally start based on location with owners address lookup.
Bills go in bills table and bill items go in bill_items table.

Bills can be sent with intents to email, texts, social media etc...
