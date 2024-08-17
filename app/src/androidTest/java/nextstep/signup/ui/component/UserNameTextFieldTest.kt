package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserNameTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            UserNameTextField(
                username = username.value,
                onNameChange = { input ->
                    username.value = input
                }
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // given
        val userNameList = listOf("김은", "김은혜", "김은혜다", "김은혜다아")

        userNameList.forEach {
            // when
            username.value = it

            // then
            composeTestRule
                .onNodeWithText(USERNAME_LENGTH_ERROR)
                .assertDoesNotExist()
        }
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // given
        val userNameList = listOf("김", "김은혜다아아")

        userNameList.forEach {
            // when
            username.value = it

            // then
            composeTestRule
                .onNodeWithText(USERNAME_LENGTH_ERROR)
                .assertExists()
        }
    }

    @Test
    fun 사용자_이름이_숫자나_기호가_포함되면_에러메시지가_노출된다() {
        // given
        val inputUserName = "김은혜!"

        // when
        username.value = inputUserName

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_CHARACTER_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // given
        val inputEmail = "김은혜!"

        // when
        username.value = inputEmail

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_CHARACTER_ERROR)
            .assertExists()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_INVALID_CHARACTER_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
        private const val EMAIL_TYPE_ERROR = "이메일 형식이 올바르지 않습니다."
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
        private const val PASSWORD_MISMATCH_ERROR = "비밀번호가 일치하지 않습니다."
    }
}