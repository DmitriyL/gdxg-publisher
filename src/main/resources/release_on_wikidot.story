Scenario: Gdxg release
Given browser maximize
Given release info:
| gameVersion | libVersion | releaseChangeLog |
| 0.03 | 2 | Change1\nChange2 |
When upload release on Dropbox
When add release info on Wikidot
Then everybody happy