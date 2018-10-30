/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.sample.custom.web.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.cloud.sample.custom.web.model.Custom;
import org.springframework.cloud.sample.custom.web.repository.CustomRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomService {
	private CustomRepository repository;

	public CustomService(CustomRepository customRepository) {
		this.repository = customRepository;
	}

	public Custom createCustom(String storeId) {
		Custom custom = new Custom(storeId);

		return repository.save(custom);
	}

	public Custom createCustom() {
		return createCustom(generateRandomId());
	}

	public Custom getCustom(String storeId) {
		Optional<Custom> store = repository.findById(storeId);
		return store.orElseThrow(() -> new IllegalArgumentException("Invalid custom store ID " + storeId + "."));
	}

	public void deleteCustom(String id) {
		repository.deleteById(id);
	}

	private String generateRandomId() {
		return UUID.randomUUID().toString();
	}
}
