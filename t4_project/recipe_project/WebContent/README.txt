#1. 임의 계정
	ID :admin@dongduk.ac.kr * PW : 123

#2. 사이트 소개
	Home : '일반 레시피', 'SNS 인기 레시피', '나만의 레시피'에서 조회수가 가장 높은 레시피가 보여진다.
	재료 맞춤 레시피 : 재료에 맞는 레시피를 추천 받을 수 있다.
	일반 레시피 : 사용자가 추가한 레시피가 아니라 DB쪽에서 직접 입력한 레시피가 보여진다.
	SNS 인기 레시피 : 사용자가 추가한 레시피가 아니라 DB쪽에서 직접 입력한 레시피가 보여진다. 주로 SNS상에 있는 레시피들이다.
	나만의 레시피 : 사용자들이 입력한 레시피들이다. 상단에 있는 레시피 추가 버튼으로 레시피를 추가할 수 있다.
	My page : 내가 등록한 레시피들을 보고, 이메일 ID를 제외한 것들을 수정할 수 있다. 회원 탈퇴를 하면 그동안 작성한 레시피는 모두 사라진다.

#3. 회원가입
	이메일 주소, 비밀번호, 비밀번호 확인, 닉네임은 필수로 입력해야 하고 빈 칸이면 alert가 뜬다.
	비선호 재료는 필수는 아니며 DB에 등록되어 있는 재료로만 한가지를 입력해야 회원가입이 된다.
	회원가입 실패 시 따로 alert가 뜨지는 않는다.

#4. 레시피 이름으로 검색
	상단 검색 창에 레시피 이름의 일부를 입력하여 레시피를 검색할 수 있다.
	검색 범위를 전체 뿐만 아니라 일반 레시피, SNS 인기 레시피, 나만의 레시피로 나눠서 검색할 수 있다.
	레시피를 클릭하면 조회수가 증가하며 자신의 레시피는 조회수가 증가하지 않는다.

#5. 재료 맞춤 레시피 검색
	재료를 입력받으면 해당 재료가 들어간 요리의 레시피가 검색된다. 
	재료 입력은 최대 3개까지 받을 수 있으며, 재표 여러 개를 입력 하면 그 중 하나라도 들어간 레시피는 전부 추천된다.

#6. 레시피 추가
	레시피 이름, 재료 정보, 소요 시간, 조리 과정을 필수로 입력해야 한다.
	재료는 재료 추가 버튼으로 입력 창을 추가하고, 삭제 버튼으로 입력 창을 삭제할 수 있다. 
	재료는 DB에 재료 정보가 저장된 재료만 입력되어야하고 잘못된 입력을 받으면 결과로 나오는 레시피 창이 비어있다. 
	조리과정 입력 시 칸이 정렬된 순서가 아니라 순서 칸에 1부터 입력된 순으로 저장된다. 
	올바르게 입력되면 입력한 레시피가 결과로 보인다.

#7. 레시피 수정, 삭제
	자신이 작성한 레시피는 하단에 수정과 삭제 버튼이 있다. 
	레시피 수정은 재료와 조리 과정에 대한 칸 추가는 불가능 하며 입력 칸을 빈칸으로 두면 삭제는 가능하다.

#8. 회원 정보 수정
	닉네임, 비밀번호, 비선호 재료를 수정할 수 있다.
	비선호 재료는 회원가입 시와 마찬가지로 DB에 이미 등록되어있는 재료만 한가지 등록 가능하다.