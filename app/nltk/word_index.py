#!/usr/bin/env python
#-*- coding: utf-8 -*-

import nltk
from nltk.stem.wordnet import WordNetLemmatizer


file_name = 'input.txt'

lemmatizer = WordNetLemmatizer()
indexes = {}
index = 1

f = open(file_name, 'r')

for line in f.readlines():
    tokens = nltk.word_tokenize(line)
    for token in tokens:
        lemma = lemmatizer.lemmatize(token)
        if lemma not in indexes:
            indexes[lemma] = index
            index += 1

        print str(indexes[lemma]) + " - " + token