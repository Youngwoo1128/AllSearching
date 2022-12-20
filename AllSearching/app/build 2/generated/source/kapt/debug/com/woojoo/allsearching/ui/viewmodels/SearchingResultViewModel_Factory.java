// Generated by Dagger (https://dagger.dev).
package com.woojoo.allsearching.ui.viewmodels;

import com.woojoo.allsearching.domain.usecases.GetAllResearchingUseCase;
import com.woojoo.allsearching.domain.usecases.InsertResearchingUseCase;
import com.woojoo.allsearching.domain.usecases.SearchResultUseCase;
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
public final class SearchingResultViewModel_Factory implements Factory<SearchingResultViewModel> {
  private final Provider<SearchResultUseCase> searchResultUseCaseProvider;

  private final Provider<InsertResearchingUseCase> insertResearchingUseCaseProvider;

  private final Provider<GetAllResearchingUseCase> getAllResearchingUseCaseProvider;

  public SearchingResultViewModel_Factory(Provider<SearchResultUseCase> searchResultUseCaseProvider,
      Provider<InsertResearchingUseCase> insertResearchingUseCaseProvider,
      Provider<GetAllResearchingUseCase> getAllResearchingUseCaseProvider) {
    this.searchResultUseCaseProvider = searchResultUseCaseProvider;
    this.insertResearchingUseCaseProvider = insertResearchingUseCaseProvider;
    this.getAllResearchingUseCaseProvider = getAllResearchingUseCaseProvider;
  }

  @Override
  public SearchingResultViewModel get() {
    return newInstance(searchResultUseCaseProvider.get(), insertResearchingUseCaseProvider.get(), getAllResearchingUseCaseProvider.get());
  }

  public static SearchingResultViewModel_Factory create(
      Provider<SearchResultUseCase> searchResultUseCaseProvider,
      Provider<InsertResearchingUseCase> insertResearchingUseCaseProvider,
      Provider<GetAllResearchingUseCase> getAllResearchingUseCaseProvider) {
    return new SearchingResultViewModel_Factory(searchResultUseCaseProvider, insertResearchingUseCaseProvider, getAllResearchingUseCaseProvider);
  }

  public static SearchingResultViewModel newInstance(SearchResultUseCase searchResultUseCase,
      InsertResearchingUseCase insertResearchingUseCase,
      GetAllResearchingUseCase getAllResearchingUseCase) {
    return new SearchingResultViewModel(searchResultUseCase, insertResearchingUseCase, getAllResearchingUseCase);
  }
}
