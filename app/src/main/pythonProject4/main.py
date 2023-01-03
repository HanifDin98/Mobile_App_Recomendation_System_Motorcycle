import numpy as np
import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel
from os.path import dirname, join

filename = join(dirname(__file__), "motor_info.csv")
df1 = pd.read_csv(filename)

tfidf = TfidfVectorizer(stop_words='english')
# replace NaN with empty string
df1['Specification'] = df1['Specification'].fillna('')
# Construct the required TF-IDF matrix by fitting and transforming data
tfidf_matrix = tfidf.fit_transform(df1['Specification'])
tfidf_matrix.shape

# compute cosine similarity matrix
cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)

# construct a reverse map of indices and motor name
indices = pd.Series(df1.index, index=df1['Motor_Name']).drop_duplicates()


def get_recomendations(title, Index,cosine_sim=cosine_sim):
    idx = indices[title]

    sim_scores = list(enumerate(cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:6]
    motor_indices = [i[0] for i in sim_scores]
    reccomendation = np.array(df1['Motor_Name'].iloc[motor_indices])
    return reccomendation[Index]
    # return df1['Motor_Name'].iloc[movie_indices]

# print(get_recomendations("EX5"))



