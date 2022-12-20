// Generated by Dagger (https://dagger.dev).
package com.woojoo.allsearching.domain.usecases;

import com.woojoo.allsearching.domain.repository.ResearchingRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class GetAllResearchingUseCase_Factory implements Factory<GetAllResearchingUseCase> {
  private final Provider<ResearchingRepository> researchingRepositoryProvider;

  public GetAllResearchingUseCase_Factory(
      Provider<ResearchingRepository> researchingRepositoryProvider) {
    this.researchingRepositoryProvider = researchingRepositoryProvider;
  }

  @Override
  public GetAllResearchingUseCase get() {
    return newInstance(researchingRepositoryProvider.get());
  }

  public static GetAllResearchingUseCase_Factory create(
      Provider<ResearchingRepository> researchingRepositoryProvider) {
    return new GetAllResearchingUseCase_Factory(researchingRepositoryProvider);
  }

  public static GetAllResearchingUseCase newInstance(ResearchingRepository researchingRepository) {
    return new GetAllResearchingUseCase(researchingRepository);
  }
}