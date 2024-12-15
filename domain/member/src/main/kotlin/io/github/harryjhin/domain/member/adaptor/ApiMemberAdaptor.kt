package io.github.harryjhin.domain.member.adaptor

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
@Profile("api")
class ApiMemberAdaptor