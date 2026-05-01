package com.cms.service.Interface;

import com.cms.dto.ComplainRequest;
import com.cms.entity.Complain;

public interface ComplainService {
    Complain createComplain(ComplainRequest complainRequest);

    Complain getComplainById(Long id);
}
