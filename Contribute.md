# How to Contribute

PR 을 올려주실 때, labels 를 참고하셔서 알맞은 제목을 함께 올려주세요!
Commit Message 는 커밋 컨벤션에 맞춰서 올려주세요.

## Process

contribute 하는 방법을 소개드립니다.

### Pull Request 를 통한 Contribute

일반 contribute분께서는 0번도 진행해 주세요.

#### 0. Fork this respository(일반 contributer용)

이 repository 를 fork 해주세요!

#### 1. Clone repository

(fork 해간) repository 를 local directory 에 clone 해주세요!

```bash
# in your workspace
$ git clone https://github.com/ShinsegaePointCloneCode/Back.git
$ cd Back
```

#### 2. Commit your

```bash
$ git add .
$ git commit -m "[your description]"
$ git push origin develop
```

### 3. Register pull request for your commit

`Pull Request`를 등록해주세요.

### 4. Apply prettier

```markdown
$ prettier --write **/*.md
# or
$ npx prettier --write **/*.md
```

### Optional. Resolve Conflict

Pull Request 를 등록했는데, conflict 가 있어서 auto merge 가 안된다고 하는 경우 해당 conflict 를 해결해주세요.

```bash
# in For_Beginner
$ git remote add --track master upstream https://github.com/ShinsegaePointCloneCode
$ git fetch upstream
$ git rebase upstream/develop
# (resolve conflict in your editor)
$ git add .
$ git rebase --continue
$ git push -f origin master
```

- [Git 참고 자료] (https://github.com/ShinsegaePointCloneCode/Back/Git.md)

### Issue 를 통한 Contribute

Pull Request 방식이 익숙하시지 않은 분들은 issue 로 내용을 등록하실 수도 있습니다. 



---



## Labels for PR

- Edit typos or links
  - 오타 또는 잘못된 링크를 수정.
- Inaccurate information
  - 잘못된 정보에 대한 Fix.
- New Resources
  - 새로운 자료 추가

### 그 외 Labels

- Suggestions
  - 해당 `Repository`에 건의하고 싶은 사항에 대해서 `Issue`로 등록해주세요.
- Questions
  - 질문이 있으시면 해당 Label 과 함께 `Issue`를 등록해주세요.



_Pull Request example>_

---

### FEATURE_REQUEST

```markdown
---
name: "\U0001F31F Feature Request"
about: 새로운 기능 요청이 있다면 이 템플릿을 사용해주세요.
title: ''
labels: enhancement
assignees: ''

---

### 기능 설명
원하는 기능에 대해 간략하게 설명해주세요.

### 사용 사례
이 기능이 필요한 상황이나 환경에 대해 설명해주세요.

### 대안
가능한 대안이나 현재 사용하고 있는 솔루션에 대해 설명해주세요.

### 추가 정보
기능 요청과 관련하여 추가 정보가 있다면 여기에 적어주세요.
```

### BUG_REPORT

```
---
name: "\U0001F41B Bug Report"
about: 버그를 발견했다면 이 템플릿을 사용해주세요.
title: ''
labels: custom
assignees: ''

---

### 버그 설명
어떤 문제가 발생했는지 간략하게 설명해주세요.

### 재현 방법
버그를 재현하기 위한 단계를 아래에 적어주세요:
1. '...'로 이동
2. '....'를 클릭
3. '....'을 볼 수 있음

### 예상한 동작
원래 어떻게 동작해야 했는지 설명해주세요.

### 스크린샷
가능하다면 문제 상황을 보여주는 스크린샷을 첨부해주세요.

### 환경
- OS:
- Browser:
- Version:

### 추가 정보
문제와 관련하여 추가 정보가 있다면 여기에 적어주세요.
```

### CUSTOM_TEMPLATE

```markdown
---
name: "\U0001F4DD Custom Template"
about: 특별한 요청이나 다른 종류의 이슈에 대해 이 템플릿을 사용해주세요.
title: ''
labels: custom
assignees: ''

---

### 요청 내용
요청하려는 내용이나 이슈에 대해 간략하게 설명해주세요.

### 관련 자료
이슈와 관련된 링크나 파일 등을 첨부해주세요.

### 추가 정보
이슈와 관련하여 추가 정보가 있다면 여기에 적어주세요.
```



### PULL_REQUEST_TEMPLATE

```markdown
name: 🚀 Pull Request

### 변경 사항 요약
변경된 내용에 대한 간단한 요약을 적어주세요.

### 변경 사유
변경 사항이 필요한 이유를 설명해주세요.

### 변경된 내용
변경된 코드나 추가된 기능에 대한 자세한 설명을 적어주세요.

### 관련 이슈
해당 풀 리퀘스트와 관련된 이슈가 있다면 링크를 적어주세요. 예: `#123`

### 테스트 방법
변경된 코드의 테스트 방법을 설명해주세요.

### 추가 정보
기타 풀 리퀘스트와 관련된 추가 정보가 있다면 여기에 적어주세요.

### 체크리스트
- [ ] 적절한 브랜치로 요청이 제출되었는가?
- [ ] `npm run lint` 를 실행하고 모든 경고와 오류를 해결하였는가?
- [ ] 새로운 테스트를 작성하였고, 기존의 테스트를 모두 통과하였는가?
- [ ] 관련 문서를 업데이트하였는가?
```
