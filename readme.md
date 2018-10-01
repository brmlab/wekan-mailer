# Wekan mailer

## About

This is small application which provides email to board function for wekan.
It is supposed to run in docker compose alongside [wekan+mongodb](https://github.com/wekan/wekan-mongodb), but may be used separately as it is Java based.
**The application is currently WIP, use at your own risk!**

## Functions

- read emails from single SMTP server account
- create card from received email on single wekan board in target list

*Note: These are just starting functions, other may or may not be added later.*

## Configuration

The configuration is passed to the wekan-mailer trough environment variables simmilar to the approach in wekan+mongodb is compose used.

### Wekan

Required env vars are:

- `WEKAN_URL`=http://localhost:3000
- `WEKAN_USER`=someuser
- `WEKAN_PASSWORD`=somepass
- `WEKAN_TARGET_BOARD`=someId
- `WEKAN_TARGET_LIST`=someListId

```env
WEKAN_URL=http://localhost:3000
WEKAN_USER=someuser
WEKAN_PASSWORD=somepass
WEKAN_TARGET_BOARD=someId
WEKAN_TARGET_LIST=someListId
```
