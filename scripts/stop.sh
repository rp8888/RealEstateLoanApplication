#!/bin/bash

ps -ef | grep artifact_name.jar | grep -v grep | awk '{print $2}' | xargs kill