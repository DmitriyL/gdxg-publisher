Scenario: Gdxg release
Given browser maximize
Given release info:
| gameVersion | libVersion  | releaseChangeLog |
| 0.04        | 1           | 3d landscape |
When upload release on Dropbox
When add release info on Wikidot
Then everybody happy