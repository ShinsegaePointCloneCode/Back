## develop에서 작업한 경우

만약 develop 브랜치에서 작업을 해서 커밋까지 완료한 상태라면, 

그 커밋을 feature 브랜치로 옮기는 작업을 할 수 있습니다. 아래는 그 방법에 대한 설명입니다:

현재의 작업 상태를 확인합니다.



## 방법

#### 최근 커밋을 확인

```
git status
```

#### 로그 확인

```
git log
```

이때 log를 통해 해당 커밋 내용의 해시를 따로 메모장에 적어둡니다. 또는 intellij내 git에서 cherry-pick기능을 활용합니다.

#### feature 브랜치로 전환

```
git checkout feature
```

#### develop 커밋 내역 가져오기

```
git cherry-pick {commit_hash}
```

#### develop 브랜치로 돌아와서 해당 커밋을 되돌리기

```
git checkout develop
git reset --hard HEAD~1
```


위의 과정을 통해 develop에서의 작업을 feature 브랜치로 옮길 수 있습니다. 단, 만약 충돌이 발생한다면 충돌을 수동으로 해결해야 할 수도 있습니다.