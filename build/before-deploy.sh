#!/usr/bin/env bash

if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_d39b6f1d3f80_key -iv $encrypted_d39b6f1d3f80_iv -in build/codesigning.asc.enc -out build/codesigning.asc -d
    gpg --fast-import build/signingkey.asc
fi